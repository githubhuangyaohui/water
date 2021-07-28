package com.whut.water.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Customer;
import com.whut.water.entities.History;
import com.whut.water.entities.Worker;
import com.whut.water.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public PageInfo<History> listHistoryForPage(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SiZE_HISTORY);
        List<History> histories = historyMapper.listHistory();
        PageInfo<History> historyPageInfo = new PageInfo<>(histories);
        return historyPageInfo;
    }

    @Override
    public PageInfo<History> searchHistory(Integer pageNum, String start, String end) {
        PageHelper.startPage(pageNum,PAGE_SiZE_HISTORY);
        List<History> histories = historyMapper.searchHistory(start,end);
        PageInfo<History> historyPageInfo = new PageInfo<>(histories);
        return historyPageInfo;
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

    @Override
    public List<Map> getSalary(String start, String end) {
        return historyMapper.getSalary(start,end);
    }

    @Override
    public List<Map> getCount() {
        return historyMapper.getCount();
    }
}
