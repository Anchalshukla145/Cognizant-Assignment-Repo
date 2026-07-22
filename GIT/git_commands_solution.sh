#!/bin/bash
# Solution script for Git Hands-On Labs (HOL 1 to HOL 5)

echo "=== HOL 1: Initializing Git Repo and Configuring User ==="
git config --global user.name "John Doe"
git config --global user.email "johndoe@example.com"
git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"

mkdir -p GitDemo
cd GitDemo
git init
echo "Welcome to Git Version Control System!" > welcome.txt
git add welcome.txt
git commit -m "Initial commit: Added welcome.txt file"

echo "=== HOL 2: Ignore unwanted files using .gitignore ==="
echo "*.log" > .gitignore
echo "logs/" >> .gitignore
echo "temp.log" > app.log
git status
git add .gitignore
git commit -m "Add .gitignore to ignore log files"

echo "=== HOL 3: Branching & Merging ==="
git checkout -b GitNewBranch
echo "Feature content" > feature.txt
git add feature.txt
git commit -m "Add feature.txt in GitNewBranch"
git checkout main 2>/dev/null || git checkout master
git diff main..GitNewBranch 2>/dev/null || git diff master..GitNewBranch
git merge GitNewBranch
git log --oneline --graph --decorate --all
git branch -d GitNewBranch

echo "=== HOL 4: Merge Conflict Resolution ==="
git checkout -b GitWork
cat << 'EOF' > hello.xml
<?xml version="1.0" encoding="UTF-8"?>
<greeting>
    <message>Hello from GitWork branch!</message>
</greeting>
EOF
git add hello.xml
git commit -m "GitWork: Added hello.xml"

git checkout main 2>/dev/null || git checkout master
cat << 'EOF' > hello.xml
<?xml version="1.0" encoding="UTF-8"?>
<greeting>
    <message>Hello from Main branch!</message>
</greeting>
EOF
git add hello.xml
git commit -m "Main: Added hello.xml with main branch content"

git merge GitWork || true
cat << 'EOF' > hello.xml
<?xml version="1.0" encoding="UTF-8"?>
<greeting>
    <message>Hello from Main branch and GitWork combined!</message>
</greeting>
EOF
echo "*.orig" >> .gitignore
git add hello.xml .gitignore
git commit -m "Resolved merge conflict in hello.xml"
git branch -d GitWork

echo "=== HOL 5: Clean up and Push ==="
git status
git log --oneline -n 5
echo "Git Hands-On Exercises completed successfully!"
