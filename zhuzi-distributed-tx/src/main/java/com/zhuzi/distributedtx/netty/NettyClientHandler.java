package com.zhuzi.distributedtx.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuzi.distributedtx.transactional.TransactionalType;
import com.zhuzi.distributedtx.transactional.ZhuziTxParticipant;
import com.zhuzi.distributedtx.transactional.ZhuziTx;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext channelHandlerContext;

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println("接收到事务管理者的最终决断：" + msg.toString());
        
        // 反序列化解析JSON数据
        JSONObject data = JSON.parseObject((String) msg);
        String groupId = data.getString("groupId");
        String command = data.getString("command");
        System.out.println("接收command：" + command);

        // 对事务进行操作
        ZhuziTx zhuziTx = ZhuziTxParticipant.getZhuziTransactional(groupId);

        // 如果事务管理者最终决定提交事务
        if ("commit".equals(command)){
            // 根据groupID找到子事务并设置commit状态
            zhuziTx.setTransactionalType(TransactionalType.commit);
        }
        // 如果事务管理者最终决定回滚事务
        else{
            // 根据groupID找到子事务并设置rollback回滚状态
            zhuziTx.setTransactionalType(TransactionalType.rollback);
        }

        // 唤醒在之前阻塞的、负责提交/回滚事务的线程
        zhuziTx.getTask().signalTask();
    }

    public void sendData(JSONObject result){
        System.out.println("向事务管理者发送数据：" + result.toJSONString());
        channelHandlerContext.writeAndFlush(result.toJSONString());
    }


}









