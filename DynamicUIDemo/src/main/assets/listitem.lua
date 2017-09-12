--
-- User: liuhonghai
-- Date: 2017/9/8
-- Time: 20:41
--
require "LRelativeLayout"
require "LTextView"
require "LImage"

local numText=LTextView:newTextView()
local iconImg =LImage:newImageView()
local contributionText =LTextView:newTextView()
local rootRelativeLayout=LRelativeLayout:newLayout()

function getView()

    local textParam=LRelativeLayout.newLayoutParams(rootRelativeLayout.WRAP_CONTENT,rootRelativeLayout.WRAP_CONTENT)
    rootRelativeLayout:addView(numText,textParam)

    local iconParam=LRelativeLayout.newLayoutParams(200,100)
    iconParam:addRule(rootRelativeLayout.BELOW,numText:getId())
    rootRelativeLayout:addView(iconImg,iconParam)


    local contributionParam=LRelativeLayout.newLayoutParams(rootRelativeLayout.WRAP_CONTENT,rootRelativeLayout.WRAP_CONTENT)
    contributionParam:addRule(rootRelativeLayout.ALIGN_PARENT_RIGHT)
    contributionParam:setMargins(0,0,200,0)
    rootRelativeLayout:addView(contributionText,contributionParam)

    return rootRelativeLayout
end

function setData(data)
    if numText ~=nil then
        numText:setText(""..data:getNum().."hello world")
    end

    if iconImg ~=nil then
        iconImg:setNetUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2355408163,617371546&fm=27&gp=0.jpg")
    end

    if contributionText ~=nil then
        contributionText:setText(""..data:getNum().."hello")
    end
end