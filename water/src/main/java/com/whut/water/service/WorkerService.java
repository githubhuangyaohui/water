package com.whut.water.service;

import com.github.pagehelper.PageInfo;
import com.whut.water.entities.Worker;

import java.util.List;
import java.util.Map;

public interface WorkerService {
    List<Worker> listWorker();
    List<Worker> searchWorker(String worker);
    /**
     * 每页数量
     */
    public final static int PAGE_SiZE_WORKER = 4;

    /**
     * 查询列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    public PageInfo<Worker> listWorkerForPage(Integer pageNum );

    /**
     * 表单搜索分页
     * @param pageNum 当前页码
     * @param searchName 员工名称
     * @return 分页对象
     */
    PageInfo<Worker> searchWorker(Integer pageNum, String searchName);



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
