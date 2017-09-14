--
-- Created by IntelliJ IDEA.
-- User: lee
-- Date: 2017/9/11
-- Time: 14:30
-- To change this template use File | Settings | File Templates.
--

--image = require "LImage"
button = require "LButton"
relativeLayout =

--image.setWH(500,500)
--image.setUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2355408163,617371546&fm=27&gp=0.jpg")

button.setText("demo")
button.setTextColor ("#AEEEEE")




ALIGN_PARENT_RIGHT = 1;
CENTER_VERTICAL = 2
button.setWH(x,x)
relativeLayout.addRule(ALIGN_PARENT_RIGHT)
relativeLayout.addRule(CENTER_VERTICAL)
relativeLayout.setMargins(20,0,20,0)
relativeLayout.setPadding(5,5,5,5)
relativeLayout.addView(button)
addRule(RIGHT_OF,button)
relativeLayout.onClick(function()
    print("跳转Activity")
    activity.setIntentKV("key", "value");
    activity.skip("com.iqyi.paopao.demo.listitem.ListDemoActivity")
end)


