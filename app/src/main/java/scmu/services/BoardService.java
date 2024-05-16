package main.java.scmu.services;

import main.java.scmu.data.*;
import main.java.scmu.db.BoardRepository;
import main.java.scmu.srv.MainApplication;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class BoardService {

    public static Board register(Board board) {
        //Check for nulls
        if (board.getId() == null || board.getRotation() == null)
            throw new NotFoundException();

        BoardDAO boardDAO = new BoardDAO(board);

        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        board = boardDB.create(boardDAO).toBoard();

        DataService.addBulk(boardDAO, board.getData());
        StatusService.addBulk(boardDAO, board.getStatus());

        return board;
    }

    public static Board updateUser(String id, Board board) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        //Check for nulls
        if (board.getRotation() != null)
            boardDAO.setRotation(board.getRotation());

        boardDAO.setActive(board.isActive());
        boardDAO.setDuration(board.getDuration());
        boardDAO.setHourToStart(board.getHourToStart());
        boardDAO.setState(board.getState());

        DataService.addBulk(boardDAO, board.getData());
        StatusService.addBulk(boardDAO, board.getStatus());
        return boardDB.put(boardDAO).toBoard();
    }

    public static Board updateArduino(String id, Board board) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        int oldState = boardDAO.getState();

        boardDAO.setState(board.getState());
        boardDAO.setCurrentState(board.getCurrentState());
        boardDAO.setLastUpdate(board.getLastUpdate());
        DataService.addBulk(boardDAO, board.getData());
        StatusService.addBulk(boardDAO, board.getStatus());

        board = boardDB.put(boardDAO).toBoard();
        board.setState(oldState);
        return board;
    }


    public static Board get(String id) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        return boardDB.get(id).toBoard();
    }

    public static Object delete(String id) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        DataService.removeAllByID(id);
        StatusService.removeAllByID(id);

        return boardDB.delete(boardDAO);
    }

    public static Board reset(String id) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        DataService.removeAllByID(id);
        StatusService.removeAllByID(id);

        return boardDAO.toBoard();
    }

    public static BoardInfo boardInfo(String id, long start, long end) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        List<Data> data = DataService.list(id, start, end);
        List<Status> status = StatusService.list(id, start, end);

        return new BoardInfo(boardDAO.toBoard(), data, status, start, end);
    }

    public static List<Board> list() {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        return boardDB.list().stream().map(BoardDAO::toBoard).toList();
    }

}
