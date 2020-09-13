package com.hackerstudy.adminclient.controller.tasks;

import com.hackerstudy.adminclient.service.DataAsycService;
import com.hackerstudy.adminclient.tasks.AsyncTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Future;

@Api(description = "多线程接口")
@RestController
public class AsyncTaskController {

	public static final Logger logger = LoggerFactory.getLogger(AsyncTaskController.class);

	@Autowired
    private AsyncTask asyncTask;

	@Autowired
    private DataAsycService dataAsycService;

	@ApiOperation(value="多线程读取数据", notes="多线程读取数据")
    @RequestMapping("asynctasks/test1")
    public String test1() throws Exception {
    	
    	long start = System.currentTimeMillis();
    	
    	Future<Boolean> a = asyncTask.doTask11();
    	Future<Boolean> b = asyncTask.doTask22();
    	Future<Boolean> c = asyncTask.doTask33();
    	
    	while (!a.isDone() || !b.isDone() || !c.isDone()) {
    		if (a.isDone() && b.isDone() && c.isDone()) {
    			break;
    		}
    	}
    	
    	long end = System.currentTimeMillis();
    	
    	String times = "任务全部完成，总耗时：" + (end - start) + "毫秒";
    	System.out.println(times);
    	
    	return times;
    }

	/**
	 * 异步读写打印文件内容
	 */
	@ApiOperation(value="多线程读写多文件", notes="多线程读写多文件")
	@GetMapping("asynctasks/readAndWriteFileAsyc")
    public void  readAndWriteFileAsyc(){
		File fileReadFolder = new File("D:\\Download\\temp");
		String fileWriteFolder = "D:\\Download\\writeTemp";
		File fileWrite = new File(fileWriteFolder);
		if(fileReadFolder.exists()){
			if(!fileWrite .exists()  && !fileWrite .isDirectory()){
				fileWrite.mkdirs();
			}
			try {
				File [] files = fileReadFolder.listFiles();
				String [] names = fileReadFolder.list();
				int i = 0;
				for(File file:files){
					String fileName = names[i];
					File fileWritePath = new File(fileWriteFolder+File.separator+fileName);
					dataAsycService.readAndWriteFileAsyc(file,fileWritePath);
					i++;
				}
			}catch (IOException e) {
				e.printStackTrace();
				logger.error("文件异步读写错误",e.getMessage());
			}
		}else{
			logger.error("该文件夹不存在");
		}
	}
}
