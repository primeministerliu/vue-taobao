package io.renren.service.buycart;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.entity.buycart.BuyCart;
import io.renren.vo.BuyCartVo;

import java.util.List;

/**
 * 订单
 * @author admin
 */
public interface BuyCartService extends IService<BuyCart> {

    /**
     * 分页查询购物列表
     * @param userId
     * @return  购物列表
     */
    public List<BuyCartVo> getCart(Long userId);

    /**
     * 删除购物项
     * @param skuId sku
     * @param userId 用户
     */
    void deleteCartItem(Integer skuId, Long userId);

    /**
     * 改变购物项数量
     * @param skuId
     * @param number +1 还是 -1
     * @return 数量
     */
    int addCartItemCount(Integer skuId, Integer number);
}
