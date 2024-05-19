package main.java.scmu.services;

import main.java.scmu.data.*;
import main.java.scmu.db.BoardRepository;
import main.java.scmu.srv.MainApplication;
import main.java.scmu.utils.Hash;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class BoardService {

    public static Board register(Board board) {
        //Check for nulls
        if (board.getId() == null || board.getPwd() == null || board.getRotation() == null)
            throw new NotFoundException();

        BoardDAO boardDAO = new BoardDAO(board);
        String hashedPwd = Hash.of(board.getPwd());
        boardDAO.setPwd(hashedPwd);

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

    public static Board request(String id, int status) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        boardDAO.setState(status);
        return boardDB.put(boardDAO).toBoard();
    }

    public static Board updateArduino(String id, Board board) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        int oldState = boardDAO.getState();

        boardDAO.setState(board.getState());
        boardDAO.setCurrentState(board.getCurrentState());
        boardDAO.setCurrentDate(board.getCurrentDate());
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

    public static Board get(String id, String pwd) {
        String hashedPwd = Hash.of(pwd);
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();

        BoardDAO boardDAO = boardDB.get(id);
        if(boardDAO.getPwd().equals(hashedPwd))
            return boardDB.get(id).toBoard();

        throw new NotFoundException();
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

    public static BoardInfo boardInfo(String id, int days) {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        BoardDAO boardDAO = boardDB.get(id);

        long lastDate = boardDAO.getCurrentDate();
        long startDate = lastDate - (long) days * 24 * 60 * 60;

        List<Data> data = DataService.list(id, startDate, lastDate);
        List<Status> status = StatusService.list(id, startDate, lastDate);

        return new BoardInfo(boardDAO.toBoard(), data, status, startDate, lastDate);
    }

    public static List<Board> list() {
        BoardRepository boardDB = MainApplication.DB_LAYER.getBoardsRepository();
        return boardDB.list().stream().map(BoardDAO::toBoard).toList();
    }

}
