package com.whut.water.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Worker;
import com.whut.water.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerMapper workerMapper;

    @Override
    public List<Worker> listWorker() {
        return workerMapper.listWorker();
    }

    @Override
    public List<Worker> searchWorker(String worker) {
        return workerMapper.searchWorker(worker);
    }

    @Override
    public PageInfo<Worker> listWorkerForPage(Integer pageNum) {
        PageHelper.startPage(pageNum,PAGE_SiZE_WORKER);
        List<Worker> workers = workerMapper.listWorker();
        PageInfo<Worker> workerPageInfo = new PageInfo<>(workers);
        return workerPageInfo;
    }

    @Override
    public PageInfo<Worker> searchWorker(Integer pageNum, String searchName) {
        PageHelper.startPage(pageNum,PAGE_SiZE_WORKER);
        List<Worker> workers = workerMapper.searchWorker(searchName);
        PageInfo<Worker> workerPageInfo = new PageInfo<>(workers);
        return workerPageInfo;
    }



    @Override
    public int deleteWorker(Integer wid) {
        return workerMapper.deleteWorker(wid);
    }

    @Override
    public int insertWorker(Worker worker) {
        return workerMapper.insertWorker(worker);
    }

    @Override
    public int updateWorker(Worker worker) {
        return workerMapper.updateWorker(worker);
    }

    @Override
    public int adjustSalary(Integer wid, Integer workerSalary) {
        return workerMapper.adjustSalary(wid,workerSalary);
    }

    @Override
    public List<Map> workerMassage() {
        return workerMapper.workerMassage();
    }

    @Override
    public List<Map> searchWorkerMassage(String searchName) {
        return workerMapper.searchWorkerMassage(searchName);
    }
}
