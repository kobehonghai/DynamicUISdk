--
-- User: liuhonghai
-- Date: 2017/9/8
-- Time: 20:41
--
require "LRelativeLayout"

local mContext=context
local Textview=luajava.bindClass('android.widget.TextView')
local ImageView=luajava.bindClass("android.widget.ImageView")

local paramsLib=LRelativeLayout:newLayoutParams()

local numText
local icon


function getView()
    numText=Textview.new(mContext)
    icon=ImageView.new(mContext)

    numText:setId(1000)
    icon:setId(1001)
    icon:setBackgroundColor(0xffffff33)

    local textParam=paramsLib:getParams(paramsLib.WRAP_CONTENT,paramsLib.WRAP_CONTENT)
    LRelativeLayout.addView(numText,textParam)
    local iconParam=paramsLib:getParams(200,100,paramsLib.BELOW,numText:getId())
    LRelativeLayout.addView(icon,iconParam)

    LRelativeLayout.removeView(numText)

    return LRelativeLayout.getContent()
end

function setData(data)
    if numText ~=nil then
        numText:setText(""..data:getNum().."hello world")
    end
end