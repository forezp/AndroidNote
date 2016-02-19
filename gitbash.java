
				1.第一次使用
				cd f: 
				 git init
				 git add -A
				 git commit -m'first commit'
				 git remote add origin https://github.com/forezp/WeChatRecord.git
				 git push -u origin master

				2.在远程有仓库，如果提交

				git add -A
				git commit -m'first commit'
				git push origin master

				3.如果提示远程仓库有更新,reject ref...
				git  pull 
				git push


			4.git 一些操作命令
				echo "# WeChatRecord" >> README.md
				git init
				git add README.md
				git commit -m "first commit"
				git remote add origin https://github.com/forezp/WeChatRecord.git
				git push -u origin master

				…or push an existing repository from the command line
				git remote add origin https://github.com/forezp/WeChatRecord.git
				git push -u origin master

				git add -A
				git commit -m'fist commmit'

				$ cd d:     //打开文件夹
				$ mkdir  testgit 
				$  cd testgit
				$ pwd       //显示当前的目录
				$ git init //把这个目录变成git可以管理的仓库
				$ git add readme.txt   添加到暂存区里面去。
				$ git add dir/files     的方式添加文件
				$  git commit -m "提交注释" //告诉Git，把文件提交到仓库。
				$ git status   //查看状态
				$  git diff  readme.txt    //查看、、
				$  git log //查看文件日志
				$  git reset  --hard HEAD^   //回退
				$  cat readme.txt   //查看
				$ git checkout  -- file    //可以丢弃工作区的修改
				$  git remote add origin https://github.com/tugenhua0707/testgit.git
				$   git push  
				$  git push -u origin master
				$ git push origin master