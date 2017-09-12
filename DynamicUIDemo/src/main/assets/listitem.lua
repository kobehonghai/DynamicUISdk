--
-- User: liuhonghai
-- Date: 2017/9/8
-- Time: 20:41
--
require "LRelativeLayout"
require "LTextView"
require "LImage"

local paramsLib=LRelativeLayout:newLayoutParams()
local numText=LTextView:newTextView()
local icon=LImage:newImageView()
local contribution=LTextView:newTextView()
local paramsLib=LRelativeLayout:newLayoutParams()


function getView() 
    local textParam=paramsLib:getParams(paramsLib.WRAP_CONTENT,paramsLib.WRAP_CONTENT)
    LRelativeLayout.addView(numText,textParam)
    local iconParam=paramsLib:getParams(200,100,paramsLib.BELOW,numText:getId())
    LRelativeLayout.addView(icon,iconParam)


    local contributionParam=paramsLib:getParams(paramsLib.WRAP_CONTENT,paramsLib.WRAP_CONTENT,paramsLib.ALIGN_PARENT_RIGHT)
    LRelativeLayout.addView(contribution,contributionParam)

    LRelativeLayout.removeView(numText)


    return LRelativeLayout.getContent()
end

function setData(data)
    if numText ~=nil then
        numText:setText(""..data:getNum().."hello world")
    end

    if icon ~=nil then
        icon:setNetUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2355408163,617371546&fm=27&gp=0.jpg")
    end

    if contribution ~=nil then
        contribution:setText(""..data:getNum().."hello")
    end
end