--
-- User: liuhonghai
-- Date: 2017/9/8
-- Time: 20:41
--
require "LRelativeLayout"
require "LLinearLayout"
require "LTextView"
require "LImage"
require "layoutConstant"

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
    numText:setLTextSize(5)

    contributionText:setGravity(numText.LEFT)

    local numParam=LRelativeLayout.newLayoutParams(30,30)
    numParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    rootRelativeLayout:addLView(numText,numParam,"num")

    local iconParam=LRelativeLayout.newLayoutParams(50,50)
    iconParam:addRule(rootRelativeLayout.RIGHT_OF,numText:getId())
    iconParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    rootRelativeLayout:addLView(iconImg,iconParam,"icon")

    local contributionParam=LRelativeLayout.newLayoutParams(rootRelativeLayout.WRAP_CONTENT,rootRelativeLayout.WRAP_CONTENT)
    contributionParam:addRule(rootRelativeLayout.ALIGN_PARENT_RIGHT)
    contributionParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    contributionParam:setMargins(20,0,20,0)
    rootRelativeLayout:addLView(contributionText,contributionParam,"contributionParam")

    local nameAreaParam=LRelativeLayout.newLayoutParams(rootRelativeLayout.MATCH_PARENT,rootRelativeLayout.WRAP_CONTENT)
    nameAreaParam:addRule(rootRelativeLayout.RIGHT_OF,iconImg:getId())
    nameAreaParam:addRule(rootRelativeLayout.LEFT_OF,contributionText:getId())
    nameAreaParam:addRule(rootRelativeLayout.CENTER_VERTICAL)
    rootRelativeLayout:addLView(outerLayout,nameAreaParam,"outlayout")

    local innerLayoutParam=LLinearLayout.newLayoutParams(outerLayout.WRAP_CONTENT,outerLayout.WRAP_CONTENT)
    outerLayout:addLView(innerLayout,innerLayoutParam,"innerlayout")

    local nameParam=LLinearLayout.newLayoutParams(0,innerLayout.MATCH_PARENT,1)
    local levelParam=LLinearLayout.newLayoutParams(30,30)
    innerLayout:setOrientation(innerLayout.HORIZONTAL)
    innerLayout:addLView(nameText,nameParam,"name")
    innerLayout:addLView(levelImg,levelParam,"level")

    rootRelativeLayout:setPadding(0,50,0,50);

    return rootRelativeLayout
end

function setData(data,view)

    local numText=view:getLChildView("num")
    numText:setText(""..data:getNum())

    local iconImg=view:getLChildView("icon")
    iconImg:setNetUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2355408163,617371546&fm=27&gp=0.jpg")
    iconImg:setBackgroundColor(-256)

    local contributionText=view:getLChildView("contributionParam")
    contributionText:setText(""..data:getNum().."contribution")


    local outerLayout=view:getLChildView("outlayout")
    local innerLayout=outerLayout:getLChildView("innerlayout")

    local nameText=innerLayout:getLChildView("name")
    nameText:setText(""..data:getNum().."namesssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")

    local levelImg=innerLayout:getLChildView("level")
    levelImg:setBackgroundColor(-156)
    levelImg:setNetUrl(data:getLevel())

end