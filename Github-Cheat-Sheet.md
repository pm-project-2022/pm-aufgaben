# Github Konsolen Befehle Cheat-Sheet

## Setup Git-User

```git config --global user.name "username"``` Setzt den Usernamen in Git  
`git config --global user.email "emailadress"` Setzt die Email-Adresse des Users in Git  

---
## Init Repo
`git init` Erstellt neues leeres Repo  
`git clone url` Erstellt eine lokale Kopie eines Remote-Repos  

---
## Stage und Commit Changes
`git status` zeigt veränderte Files im Directory und ob die Files getrackt sind oder nicht  
`git add / git add file` tracked alle Files im Directory oder nur eine spezifische  
`git reset/ git reset all` untracked alle gestaged Files im Directory oder nur eine spezifische  
`git diff` listet die Changes in der Workingcopy und dem letztem Commit  
`git diff commitA commitB` listet die Changes zwischen zwei Commits  
`git commit/ git commit -m "commit beschreibung` commited die gestaged Files mit einer Beschreibung der Änderungen  

---
## Branches und Mergen
`git branch` Listet alle Branches auf  
`git branch branchname` erstellt neuen Branch  
`git checkout branchname` wechselt zu dem Branch mit dem angegeben Namen   
`git checkout -b branchname` kombiniert die vorherigen beiden Befehle  
`git merge branchname` Merged einen Branch mit dem angegeben Namen in den Aktuellen  
`git log` Zeigt alle commits im aktuellen Branch

---

## Share stuff
`git remote add alias-name für repo url` fügt dem Repo ein bestehendes Repo per URL und einem Alias hinzu  
`git fetch alias` fetched den Inhalt des Alias Repo  
`git merge alias` merged den Inhalt des Alias Repos  
`git push` pushed den Inhalt des aktuellen Branches in den Remote-Repo-Branch  mit dem angegeben Namen

---
## Untrack und Löschen
`git rm --cached filename` untracked die File  
`git rm filename` untracked und löscht die File aus dem Verzeichnis