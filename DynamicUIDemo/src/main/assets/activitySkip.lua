--
-- Created by IntelliJ IDEA.
-- User: lee
-- Date: 2017/9/11
-- Time: 14:30
-- To change this template use File | Settings | File Templates.
--

--image = require "LImage"
button = require "LButton"
activity = require "Activity"

--image.setWH(500,500)
--image.setUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2355408163,617371546&fm=27&gp=0.jpg")

button.createButton()
button.setText("demo")
button.setTextColor("#AEEEEE")
button.onClick(function()
    print("跳转Activity")
    activity.skip("com.iqyi.paopao.demo.listitem.ListDemoActivity")
end)











