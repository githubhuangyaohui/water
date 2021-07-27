package com.whut.water.service;

import com.whut.water.entities.Worker;
import com.whut.water.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
