package com.whut.water.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Customer;
import com.whut.water.entities.History;
import com.whut.water.entities.Worker;
import com.whut.water.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional(rollbackFor = {Exception.class,Error.class})
    @Override
    public int deleteHistory(Integer hid) {
        return historyMapper.deleteHistory(hid);
    }

    @Transactional(rollbackFor = {Exception.class,Error.class})
    @Override
    public int deleteHistoryBatch(String hidList) {
        ArrayList<Integer> hidArray = new ArrayList<>();
        // 前端开头多了个逗号
        hidList = hidList.replaceFirst(",","");
        // 切割成数组
        String[] split = StrUtil.split(hidList, ",");
        for (String id : split) {
            hidArray.add(Integer.parseInt(id));
        }
        return historyMapper.deleteHistoryBatch(hidArray);
    }

    @Override
    public History getHistoryById(Integer hid) {
        return historyMapper.getHistoryById(hid);
    }

    @Override
    public boolean isHaveHistoryByWid(Integer wid) {
        return historyMapper.getHistoryByWId(wid).size()>0?true:false;
    }

    @Override
    public boolean isHaveHistoryByCid(Integer cid) {
        return historyMapper.getHistoryByCId(cid).size()>0?true:false;
    }

    @Transactional(rollbackFor = {Exception.class,Error.class})
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

    @Transactional(rollbackFor = {Exception.class,Error.class})
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
