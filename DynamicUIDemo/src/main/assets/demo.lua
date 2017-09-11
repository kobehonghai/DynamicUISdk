--
-- Created by IntelliJ IDEA.
-- User: lee
-- Date: 2017/9/11
-- Time: 14:30
-- To change this template use File | Settings | File Templates.
--

button = require "LButton"

button.setText("demo")
button.setTextColor ("#AEEEEE")

function buttonClick()
    print("点击事件")
    button.setOnClickAction("com.iqyi.paopao.demo.listitem.ListDemoActivity")
    --这里或者return来处理
end






