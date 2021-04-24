package com.example.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatis.plus.dao.StudentsDao;
import com.example.mybatis.plus.entity.Students;
import com.example.mybatis.plus.service.StudentsService;
import org.springframework.stereotype.Service;

/**
 * (Students)表服务实现类
 *
 * @author makejava
 * @since 2021-04-24 15:52:49
 */
@Service
public class StudentsServiceImpl extends ServiceImpl<StudentsDao, Students> implements StudentsService {

}
