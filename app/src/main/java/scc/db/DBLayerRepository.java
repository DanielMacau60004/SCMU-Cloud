package main.java.scc.db;



public interface DBLayerRepository {

    UserRepository getUsersRepository();

    HouseRepository getHousesRepository();

    RentalRepository getRentalsRepository();

    QuestionRepository getQuestionsRepository();

    PromotionRepository getPromotionRepository();


}
