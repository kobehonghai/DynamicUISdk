--
-- Created by IntelliJ IDEA.
-- User: lee
-- Date: 2017/9/11
-- Time: 14:30
-- To change this template use File | Settings | File Templates.
--

image = require "LImage"

image.setWH(500,500)
image.setUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2355408163,617371546&fm=27&gp=0.jpg")


function buttonClick()
    print("点击事件")
    button.setOnClickAction("com.iqyi.paopao.demo.listitem.ListDemoActivity")
    --这里或者return来处理
end






