package main.java.scmu.mgt;

import com.azure.core.http.rest.PagedIterable;
import com.azure.resourcemanager.AzureResourceManager;
import com.azure.resourcemanager.search.models.QueryKey;
import com.azure.resourcemanager.search.models.SearchService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class dumps the information for a given search index in the format that
 * will be used by the Search program.
 * It also create a .sh file to set the appropriate properties.
 */
public class SearchDumpProperties
{
    private static void appendInfo( StringBuffer cmd, String name, String rgName, String propName, String value) {
        cmd.append("az functionapp config appsettings set --name ");
        cmd.append(name);
        cmd.append(" --resource-group ");
        cmd.append(rgName);
        cmd.append(" --settings \"");
        cmd.append(propName);
        cmd.append("=");
        cmd.append(value);
        cmd.append("\"\n");

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println( "java scc.mgt.SearchDumpProperties name");
            return;
        }
        String searchService = args[0];

        try {
            System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Error");

            final AzureResourceManager azure = AzureManagement.createManagementClient();
            for (int i = 0; i < AzureManagement.REGIONS.length; i++) {
                for (SearchService srv : azure.searchServices().list()) {
                    if (!srv.name().equalsIgnoreCase(searchService))
                        continue;
                    String propFilename = "search-" + AzureManagement.AZURE_PROPS_LOCATIONS[i];
                    String settingsFilename = "search-" + AzureManagement.AZURE_SETTINGS_LOCATIONS[i];
                    String appName = AzureManagement.AZURE_APP_NAME[i];
                    String functionName = AzureManagement.AZURE_FUNCTIONS_NAME[i];
                    String rgName = AzureManagement.AZURE_RG_REGIONS[i];

                    Files.deleteIfExists(Paths.get(propFilename));
                    Files.write(Paths.get(propFilename),
                            ("# Date : " + new SimpleDateFormat().format(new Date()) + "\n").getBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                    Files.write(Paths.get(propFilename), ("SearchServiceName=" + searchService + "\n").getBytes(),
                            StandardOpenOption.APPEND);
                    Files.write(Paths.get(propFilename),
                            ("SearchServiceAdminKey=" + srv.getAdminKeys().primaryKey() + "\n").getBytes(),
                            StandardOpenOption.APPEND);
                    PagedIterable<QueryKey> lst = srv.listQueryKeys();
                    if (lst == null)
                        srv.createQueryKey("newquerykey");
                    lst = srv.listQueryKeys();
                    QueryKey qk = lst.iterator().next();
                    Files.write(Paths.get(propFilename),
                            ("SearchServiceQueryKey=" + qk.key() + "\n").getBytes(),
                            StandardOpenOption.APPEND);
                    Files.write(Paths.get(propFilename), ("SearchServiceUrl=https://" + searchService + ".search.windows.net\n").getBytes(),
                            StandardOpenOption.APPEND);
                    Files.write(Paths.get(propFilename), ("IndexName=cosmosdb-index\n").getBytes(),
                            StandardOpenOption.APPEND);
                    Files.write(Paths.get(propFilename), ("ApiVersion=2023-07-01-Preview\n").getBytes(),
                            StandardOpenOption.APPEND);

                    StringBuffer cmd = new StringBuffer();
                    if (functionName != null) {
                        appendInfo( cmd, functionName, rgName, "SearchServiceName", searchService);
                        appendInfo( cmd, functionName, rgName, "SearchServiceAdminKey", srv.getAdminKeys().primaryKey());
                        appendInfo( cmd, functionName, rgName, "SearchServiceQueryKey", qk.key());
                        appendInfo( cmd, functionName, rgName, "SearchServiceUrl", "https://" + searchService + ".search.windows.net");
                        appendInfo( cmd, functionName, rgName, "IndexName", "cosmosdb-index"); //TODO: replace
                        appendInfo( cmd, functionName, rgName, "ApiVersion", "2020-06-30");
                    }
                    if (appName != null) {
                        appendInfo( cmd, appName, rgName, "SearchServiceName", searchService);
                        appendInfo( cmd, appName, rgName, "SearchServiceAdminKey", srv.getAdminKeys().primaryKey());
                        appendInfo( cmd, appName, rgName, "SearchServiceQueryKey", qk.key());
                        appendInfo( cmd, appName, rgName, "SearchServiceUrl", "https://" + searchService + ".search.windows.net");
                        appendInfo( cmd, appName, rgName, "IndexName", "cosmosdb-index"); //TODO: replace
                        appendInfo( cmd, appName, rgName, "ApiVersion", "2020-06-30");
                    }
                    synchronized (AzureManagement.class) {
                        Files.deleteIfExists(Paths.get(settingsFilename));
                        Files.write(Paths.get(settingsFilename), cmd.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
