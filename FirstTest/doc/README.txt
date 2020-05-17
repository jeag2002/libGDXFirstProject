RUN HTML https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline#running-the-html-project

gradlew html:dist

This will compile your app to Javascript and place the resulting Javascript, HTML and asset files in the html/build/dist/ folder. The contents of this folder have to be served up by a web server, e.g. Apache or Nginx. Just treat the contents like you'd treat any other static HTML/Javascript site. There is no Java or Java Applets involved!

With Python installed, you can test your distribution by executing the following in the html/build/dist folder:

Python 2.x

python -m SimpleHTTPServer

Python 3.x

python -m http.server 8000

You can then open a browser to http://localhost:8000 and see your project in action.

With Node.js npm install http-server -g then http-server html/build/dist and browse at http://localhost:8080. docs

With PHP you may type php -S localhost:8000 and browse at http://localhost:8080. docs


Box2D:
https://github.com/libgdx/libgdx/wiki/box2d#contact-listeners

Texture Atlas:
https://www.reddit.com/r/libgdx/comments/28lzxz/creating_a_texture_atlas_from_a_sprite_sheet/


java -cp d:\workspaces\runtimes\libgdx\jar_files\gdx-1.9.7.jar;d:\workspaces\runtimes\libgdx\jar_files\gdx-tools-1.9.7.jar com.badlogic.gdx.tools.imagepacker.ImagePacker d:\workspaces\workGameDev\workGDX_1\FirstTest\android\assets\player_1 d:\workspaces\workGameDev\workGDX_1\FirstTest\android\assets\player_1\res

https://www.badlogicgames.com/forum/viewtopic.php?f=11&t=23183

https://www.gamefromscratch.com/post/2014/04/15/A-quick-look-at-Tiled-An-open-source-2D-level-editor.aspx
https://www.gamefromscratch.com/post/2014/05/01/LibGDX-Tutorial-11-Tiled-Maps-Part-2-Adding-a-character-sprite.aspx

https://stackoverflow.com/questions/34375728/libgdx-map-boundaries
https://www.youtube.com/watch?v=DOpqkaX9844  

https://stackoverflow.com/questions/32302235/exporting-a-libgdx-game-as-executable-jar-from-android-studio

https://github.com/BrentAureli/ControllerDemo/tree/master/core
https://github.com/obviam/star-assault/tree/master/star-assault/src/net/obviam/starassault/controller
https://github.com/biggz/TouchPadTest


luces con box2d: https://www.youtube.com/watch?v=br05O5lTeRQ

https://gamedev.stackexchange.com/questions/88317/sprite-rotation-libgdx

https://hub.docker.com/_/httpd

instruccions for uploading GWT to EC2 AWS: https://reflectoring.io/aws-deploy-docker-image-via-web-console/
(copy from html/build/dist)
