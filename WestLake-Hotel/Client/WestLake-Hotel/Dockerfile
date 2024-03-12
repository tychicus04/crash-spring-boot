FROM nginx:alpine
COPY /dist /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/nginx.conf /etc/nginx/conf.d

ENV VITE_BASE_URL=http://tanhomedevserver.ddns.net/api/

# EXPOSE 80

WORKDIR /usr/share/nginx/html

CMD ["nginx" , "-g" , "daemon off;"]

