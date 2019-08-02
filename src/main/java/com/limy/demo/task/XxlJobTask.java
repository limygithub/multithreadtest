package com.limy.demo.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Limy
 * @create: 2019/07/24 16:58
 * @description: ${description}
 */
@JobHandler(value="XxlJobTestHandler")
@Component
@Slf4j
public class XxlJobTask extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {

        log.info("测试任务开始,执行时间"+new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss").format(new Date()));
        return SUCCESS;
    }
}