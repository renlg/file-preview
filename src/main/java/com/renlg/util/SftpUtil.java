package com.renlg.util;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtil {
	static Session session = null;
	static Channel channel = null;
	static ChannelSftp channelSftp = null;

//	private static final Logger logger = Logger.getLogger(SftpUtil.class);

	public static final String FTP_HOST_KEY = "sftp.host";
	public static final String FTP_PORT_KEY = "sftp.port";
	public static final String FTP_USERNAME_KEY = "sftp.username";
	public static final String FTP_PASSWORD_KEY = "sftp.password";
	public static final String FTP_TIMEOUT_KEY = "sftp.timeout";
	public static final int FTP_DEF_PORT = 22;
	public static final int FTP_DEF_TIMEOUT = 30;
	public static final String FTP_CONFIG_PATH = "sftp.properties";

	private SftpUtil() {

	}

	/**
	 * 默认根据配置文件初始化
	 * 
	 * @return
	 * @throws JSchException
	 */
	public static SftpUtil getChannel() throws JSchException {
		Map<String, String> prop = PropertiesUtil.readProperties(FTP_CONFIG_PATH);
		return getChannel(prop);
	}

	/**
	 * 根据自定义的配置初始化
	 * 
	 * @param sftpDetails
	 * @return
	 * @throws JSchException
	 */
	public static SftpUtil getChannel(Map<String, String> sftpDetails) throws JSchException {
		SftpUtil sftpUtil = new SftpUtil();
		String ftpHost = sftpDetails.get(FTP_HOST_KEY);// "10.54.0.124";
		String portStr = sftpDetails.get(FTP_PORT_KEY);
		String ftpUserName = sftpDetails.get(FTP_USERNAME_KEY);
		String ftpPassword = sftpDetails.get(FTP_PASSWORD_KEY);
		String timeoutStr = sftpDetails.get(FTP_TIMEOUT_KEY);
		int ftpPort = FTP_DEF_PORT;
		if (StringUtils.isNotEmpty(portStr)) {
			ftpPort = Integer.valueOf(portStr);
		}
		int timeout = FTP_DEF_TIMEOUT;
		if (StringUtils.isNotEmpty(timeoutStr)) {
			timeout = Integer.valueOf(timeoutStr);
		}

		JSch jsch = new JSch(); // 创建JSch对象
		session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
		if (ftpPassword != null) {
			session.setPassword(ftpPassword); // 设置密码
		}
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // 为Session对象设置properties
		session.setTimeout(timeout); // 设置timeout时间
		session.connect(); // 通过Session建立链接

		channel = session.openChannel("sftp"); // 打开SFTP通道
		channel.connect(); // 建立SFTP通道的连接
		channelSftp = (ChannelSftp) channel;
		return sftpUtil;
		// return (ChannelSftp) channel;
	}
	
	/**
	 * 上传文件
	 * @param src
	 * @param dst
	 * @param mode
	 * @throws SftpException
	 */
	public void putFile(String src, String dst, int mode) throws SftpException {
		File file = new File(src);
		if (!file.exists()) {
			throw new RuntimeException("本地源文件不存在");
		}
		channelSftp.put(src, dst, mode);
	}

	public void putFile(String src, String dst) throws SftpException {
		putFile(src, dst, ChannelSftp.OVERWRITE);
	}

	/**
	 * 关闭sftp
	 * 
	 * @throws Exception
	 */
	public void closeChannel() throws Exception {
		if (channel != null) {
			channel.disconnect();
		}
		if (session != null) {
			session.disconnect();
		}
	}

	public static void main(String[] args) {
		SftpUtil util = null;
		try {
			util = SftpUtil.getChannel();
			util.putFile("D:\\test\\pdf.zip", "/opt/tomcat/test.zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				util.closeChannel();
			} catch (Exception e) {
			}
		}
	}
}
