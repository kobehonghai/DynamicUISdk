--
-- User: liuhonghai
-- Date: 2017/9/8
-- Time: 20:41
--
require "LRelativeLayout"
require "LLinearLayout"
require "LTextView"
require "LImage"

local numText=LTextView:newTextView()
local iconImg =LImage:newImageView()
local nameText=LTextView:newTextView()
local levelImg=LImage:newImageView()
local contributionText =LTextView:newTextView()
local rootRelativeLayout=LRelativeLayout:newLayout()
local innerLayout =LLinearLayout:newLayout()
local outerLayout =LLinearLayout:newLayout()


function getView()

    nameText:setLSingleLine()
    nameText:setGravity(numText.CENTER)

    numText:setGravity(numText.CENTER)
    numText:setLTextBold()
    numText:setLTextColor("#000000")
    numText:setLTextSize(20)

    contributionText:setGravity(numText.LEFT)

    local numParam=LRelativeLayout.newLayoutParams(100,100)
    numParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    rootRelativeLayout:addView(numText,numParam)

    local iconParam=LRelativeLayout.newLayoutParams(200,100)
    iconParam:addRule(rootRelativeLayout.RIGHT_OF,numText:getId())
    iconParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    rootRelativeLayout:addView(iconImg,iconParam)

    local contributionParam=LRelativeLayout.newLayoutParams(rootRelativeLayout.WRAP_CONTENT,rootRelativeLayout.WRAP_CONTENT)
    contributionParam:addRule(rootRelativeLayout.ALIGN_PARENT_RIGHT)
    contributionParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    contributionParam:setMargins(20,0,20,0)
    rootRelativeLayout:addView(contributionText,contributionParam)

    local nameAreaParam=LRelativeLayout.newLayoutParams(rootRelativeLayout.MATCH_PARENT,rootRelativeLayout.WRAP_CONTENT)
    nameAreaParam:addRule(rootRelativeLayout.RIGHT_OF,iconImg:getId())
    nameAreaParam:addRule(rootRelativeLayout.LEFT_OF,contributionText:getId())
    nameAreaParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    rootRelativeLayout:addView(outerLayout,nameAreaParam)

    local innerLayoutParam=LLinearLayout.newLayoutParams(outerLayout.WRAP_CONTENT,outerLayout.WRAP_CONTENT)
    outerLayout:addView(innerLayout,innerLayoutParam)

    local nameParam=LLinearLayout.newLayoutParams(0,innerLayout.MATCH_PARENT,1)
    local levelParam=LLinearLayout.newLayoutParams(100,100)
    innerLayout:setOrientation(innerLayout.HORIZONTAL)
    innerLayout:addView(nameText,nameParam)
    innerLayout:addView(levelImg,levelParam)

    rootRelativeLayout:setPadding(0,50,0,50);

    return rootRelativeLayout
end

function setData(data)
    if numText ~=nil then
        numText:setText(""..data:getNum())
    end

    if iconImg ~=nil then
        iconImg:setNetUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2355408163,617371546&fm=27&gp=0.jpg")
        iconImg:setBackgroundColor(0x7f0a001c)
    end

    if nameText ~=nil then
        nameText:setText(""..data:getNum().."namesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
    end

    if levelImg ~=nil then
        levelImg:setBackgroundColor(-256)
    end

    if contributionText ~=nil then
        contributionText:setText(""..data:getNum().."contribution")
    end


end