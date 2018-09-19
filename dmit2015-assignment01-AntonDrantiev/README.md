cd ~/git/dmit2015-instructor-repository

git pull

cp -R dmit2015-instructor-repository/dmit2015-assignment01-start my-dmit2015-repository

cd ~/git/my-dmit2015-repository

mv dmit2015-assignment01-start dmit2015-assignment01-yourname (replace yourname with your full name, ex. johnsmith)

git add dmit2015-assignment01-yourname

git commit -m "first commit"

git remote add origin https://github.com/yourAccountName/my-dmit2015-repository.git

git push -u origin master
