package com.whut.water.service;

import com.whut.water.entities.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkerService {
    List<Worker> listWorker();
    List<Worker> searchWorker(String worker);
    int deleteWorker(Integer wid);
    int insertWorker(Worker worker);
    int updateWorker(Worker worker);
    int adjustSalary(Integer wid,Integer workerSalary);

    /**
     * 获取每个送水工统计数据
     * @return
     */
    List<Map> workerMassage();

    /**
     * 模糊搜索一个员工的送水数量
     * @param searchName
     * @return
     */
    List<Map> searchWorkerMassage(String searchName);
}
