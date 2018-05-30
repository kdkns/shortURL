FROM tomcat

ADD target/shorturl.war /usr/local/tomcat/webapps/

# Delete existing ROOT folder
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy to images tomcat path
RUN cd /usr/local/tomcat/webapps && \
    mv shorturl.war ROOT.war
