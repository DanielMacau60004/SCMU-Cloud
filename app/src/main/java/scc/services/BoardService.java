package main.java.scc.services;

import main.java.scc.data.Board;
import main.java.scc.data.BoardDAO;
import main.java.scc.db.BoardRepository;
import main.java.scc.srv.MainApplication;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class BoardService {

    public static Board register(Board board) {
        //Check for nulls
        if (board.getId() == null || board.getRotation() == null)
            throw new NotFoundException();

        BoardDAO boardDAO = new BoardDAO(board);

        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        return boardDB.create(boardDAO).toBoard();
    }

    public static Board update(String id, Board board) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        //Check for nulls
        if (board.getRotation() != null)
            boardDAO.setRotation(board.getRotation());

        boardDAO.setActive(board.isActive());
        boardDAO.setHourToStart(board.getHourToStart());
        boardDAO.setDuration(board.getDuration());


        return boardDB.put(boardDAO).toBoard();
    }

    public static Board get(String id) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        return boardDB.get(id).toBoard();
    }

    public static Object delete(String id) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        return boardDB.delete(boardDAO);
    }

    public static List<Board> list() {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        return boardDB.list().stream().map(BoardDAO::toBoard).toList();
    }

}
