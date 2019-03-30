package io.renren.service.seckill.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.dao.seckill.MiaoshaOrderDao;
import io.renren.entity.seckill.MiaoshaOrder;
import io.renren.entity.order.OrderMaster;
import io.renren.entity.user.UserEntity;
import io.renren.service.goods.GoodsService;
import io.renren.service.seckill.MiaoshaOrderService;
import io.renren.service.order.OrderMasterService;
import io.renren.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by LiuLiHao on 2019/3/7 0007 下午 03:57
 * @author : LiuLiHao
 * 描述：
 */
@Service
public class MiaoshaOrderServiceImpl extends ServiceImpl<MiaoshaOrderDao, MiaoshaOrder> implements MiaoshaOrderService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MiaoshaOrderService miaoshaOrderService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public MiaoshaOrder getOrderByGoodsIdAndUserId(Long goodsId, Long userId) {
        return baseMapper.getOrderByGoodsIdAndUserId(goodsId,userId);
    }

    @Override
    public OrderMaster secKill(GoodsVo goodsVo, UserEntity user) {
        //1 减少商品表的库存
        goodsService.decCount(goodsVo.getId());

        //2 插入数据到秒杀表
        return orderMasterService.createOrder(goodsVo,user);
    }

    @Override
    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = miaoshaOrderService.getOrderByGoodsIdAndUserId(goodsId, userId);
        if(order != null) {
            //秒杀成功
            return order.getOrderId();
        }else {
            boolean isOver = getGoodsOver(goodsId);
            if(isOver) {
                return -1;
            }else {
                return 0;
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisTemplate.opsForValue().set("goods"+goodsId, true);
    }

    private boolean getGoodsOver(long goodsId) {
        Object o = redisTemplate.opsForValue().get("goods" + goodsId);
        return o==null;
    }


}
