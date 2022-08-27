package vn.fis.logfile.vinasoy.service;

import vn.fis.logfile.vinasoy.model.History;

import java.util.List;

public interface HistoryService {
    List<History> listAll();
    History create(History history);
}
