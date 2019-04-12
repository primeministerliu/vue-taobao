package io.renren.common.entity.goods;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by LiuLiHao on 2019/3/12 0012 上午 10:09
 * @author : LiuLiHao
 * 描述：商品顶部图片
 */
@Data
@TableName("goods_img")
public class GoodsImg implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    @TableId
    @JsonIgnore
    private Long id;

    /**
     * 商品id
     */
    @JsonIgnore
    private Long goodsId;

    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 排序
     */
    @JsonIgnore
    private Integer orderNum;

}
