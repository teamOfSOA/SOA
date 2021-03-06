<xsl:stylesheet
        xmlns:std="http://jw.nju.edu.cn/schema"
        xmlns="http://jw.nju.edu.cn/schema"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/std:学生列表">
        <xsl:element name="课程成绩列表" namespace="http://jw.nju.edu.cn/schema">
            <xsl:for-each select="std:学生">
                <xsl:call-template name="template1"/>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>

    <xsl:template name="template1">
        <xsl:for-each select="std:个人成绩列表/std:课程成绩列表/std:课程成绩[@成绩性质='总评成绩']">
            <xsl:sort select="std:成绩/std:得分" data-type="number"/>
            <xsl:call-template name="template2">
                <xsl:with-param name="课程编号" select="@课程编号"/>
                <xsl:with-param name="学号" select="std:成绩/std:学号"/>
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>

    <xsl:template name="template2">
        <xsl:param name="课程编号"/>
        <xsl:param name="学号"/>
        <xsl:for-each select="//std:学生列表/std:学生/std:个人成绩列表/std:课程成绩列表/std:课程成绩">
            <xsl:if test="@课程编号=$课程编号">
                <xsl:if test="std:成绩/std:学号=$学号">
                    <xsl:element name="课程成绩">
                        <xsl:attribute name="课程编号">
                            <xsl:value-of select="@课程编号"/>
                        </xsl:attribute>
                        <xsl:attribute name="成绩性质">
                            <xsl:value-of select="@成绩性质"/>
                        </xsl:attribute>
                        <xsl:element name="成绩">
                            <xsl:element name="学号">
                                <xsl:value-of select="std:成绩/std:学号"/>
                            </xsl:element>
                            <xsl:element name="得分">
                                <xsl:value-of select="std:成绩/std:得分"/>
                            </xsl:element>
                        </xsl:element>
                    </xsl:element>
                </xsl:if>
            </xsl:if>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>


