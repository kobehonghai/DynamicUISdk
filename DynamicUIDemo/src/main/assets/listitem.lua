--
-- User: liuhonghai
-- Date: 2017/9/8
-- Time: 20:41
--
local mContext=context
local layoutParams=relativeLayoutParams --need to do better

local Textview=luajava.bindClass('android.widget.TextView')
local ImageView=luajava.bindClass("android.widget.ImageView")
local RelativeLayout=luajava.bindClass("android.widget.RelativeLayout")

local numText
local icon
local relativeLayout


function getView()
    numText=Textview.new(mContext)
    icon=ImageView.new(mContext)
    relativeLayout=RelativeLayout.new(mContext)

    numText:setId(1000)
    icon:setId(1001)
    icon:setBackgroundColor(0xffffff33)
    relativeLayout:addView(numText)

    layoutParams:addRule(RelativeLayout.RIGHT_OF,numText:getId())
    relativeLayout:addView(icon,layoutParams)
    return relativeLayout;
end

function setData(data)
    if numText ~=nil then
        numText:setText(""..data:getNum().."hello world")
    end
end