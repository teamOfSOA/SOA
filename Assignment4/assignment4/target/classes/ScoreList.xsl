<xsl:stylesheet
        xmlns:std="http://jw.nju.edu.cn/schema"
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
    <!--    <xsl:namespace-alias stylesheet-prefix="nju" result-prefix="nju"/>-->
    <xsl:template match="/std:学生列表">
        <xsl:element name="课程成绩列表">
            <xsl:for-each select="std:学生">
                <xsl:value-of select="std:学号"/>
                <xsl:call-template name="template2"/>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
    <xsl:template name="template2">
        <xsl:for-each select="个人成绩列表/课程成绩列表/课程成绩">
            <xsl:sort select="个人成绩列表/课程成绩列表/课程成绩/成绩/得分"/>
            <xsl:element name="课程成绩">
                <xsl:attribute name="课程编号">
                    <xsl:value-of select="@课程编号"/>
                </xsl:attribute>
                <xsl:attribute name="成绩性质">
                    <xsl:value-of select="@成绩性质"/>
                </xsl:attribute>
                <xsl:element name="成绩">
                    <xsl:element name="学号">
                        <xsl:value-of select="成绩/学号"/>
                    </xsl:element>
                    <xsl:element name="得分">
                        <xsl:value-of select="成绩/得分"/>
                    </xsl:element>
                </xsl:element>
            </xsl:element>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>