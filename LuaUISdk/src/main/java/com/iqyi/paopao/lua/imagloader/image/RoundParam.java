package com.iqyi.paopao.lua.imagloader.image;

/**
 * Created by LiYong on 2017/9/14.
 * Email:liyong@qiyi.com/lee131483@gmail.com
 * Version:
 * 图片圆角属性
 */

public class RoundParam {

    private boolean mRoundAsCircle = false;

    private float[] mCornersRadii = null;


    public RoundParam setRoundAsCircle(boolean roundAsCircle) {
        mRoundAsCircle = roundAsCircle;
        return this;
    }

    public boolean getRoundAsCircle() {
        return mRoundAsCircle;
    }


    private float[] getOrCreateRoundedCornersRadii() {
        if (mCornersRadii == null) {
            mCornersRadii = new float[4];
        }
        return mCornersRadii;
    }

    public static RoundParam fromCornersRadii(
            float topLeft,
            float topRight,
            float bottomRight,
            float bottomLeft) {
        return (new RoundParam())
                .setCornersRadii(topLeft, topRight, bottomRight, bottomLeft);
    }

    public RoundParam setCornersRadii(
            float topLeft,
            float topRight,
            float bottomRight,
            float bottomLeft) {
        float[] radii = getOrCreateRoundedCornersRadii();
        radii[0] = topLeft;
        radii[1] = topRight;
        radii[2] = bottomRight;
        radii[3] = bottomLeft;
        return this;
    }
}
