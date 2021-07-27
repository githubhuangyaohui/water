package com.whut.water.service;

import com.whut.water.entities.Customer;
import com.whut.water.entities.History;
import com.whut.water.entities.Worker;
import com.whut.water.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryMapper historyMapper;

    @Override
    public List<History> listHistory() {
        return historyMapper.listHistory();
    }

    @Override
    public List<History> searchHistory(String start, String end) {
        return historyMapper.searchHistory(start,end);
    }

    @Override
    public int deleteHistory(Integer hid) {
        return historyMapper.deleteHistory(hid);
    }

    @Override
    public History getHistoryById(Integer hid) {
        return historyMapper.getHistoryById(hid);
    }

    @Override
    public int updateHistory(History history,Integer custId,Integer workerId) {
        Customer customer = new Customer();
        customer.setCid(custId);
        Worker worker = new Worker();
        worker.setWid(workerId);

        history.setWorker(worker);
        history.setCustomer(customer);

        return historyMapper.updateHistory(history);
    }

    @Override
    public int insertHistory(History history, Integer custId, Integer workerId) {
        Customer customer = new Customer();
        customer.setCid(custId);
        Worker worker = new Worker();
        worker.setWid(workerId);

        history.setWorker(worker);
        history.setCustomer(customer);

        return historyMapper.insertHistory(history);
    }
}
