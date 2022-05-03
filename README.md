# kali

kali is our test framework to expose all UI regression bugs that we may encounter in our [ebloodbanking application](https://ebloodbanking.com/).

[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/jagritiinnohealth/kali/CI-to-test-the-tester)](https://github.com/jagritiinnohealth/kali/actions)
![GitHub contributors](https://img.shields.io/github/contributors/jagritiinnohealth/kali)
![GitHub last commit](https://img.shields.io/github/last-commit/jagritiinnohealth/kali)

Inspiration of project name: [Kali-Slayer_of_Raktabīja](https://en.wikipedia.org/wiki/Kali#Slayer_of_Raktab%C4%ABja)

## Getting Started

- Clone this project: `git clone https://github.com/jagritiinnohealth/kali.git`
- Install below 3 external dependencies on your machine:
  - **[Java 11](https://openjdk.java.net/projects/jdk/11/)** (as the core programming language)
  - **[Maven 3.8.5](https://maven.apache.org/download.cgi)** (for dependency management)
  - **[Google Chrome latest version](https://www.google.com/chrome/?brand=CHBD&gclid=Cj0KCQjwr-SSBhC9ARIsANhzu15P0PA-n9Zp4NpxKaOHVGtBD1TZQH0HlQQE6hUfsOFAU1nf-Rzdlf4aAoTJEALw_wcB&gclsrc=aw.ds)** (browser to run your tests)

> If your JAVA_HOME is set to anything other than JDK 11, you would need to update the path. Else your project
> will not run. Also, do remember to set the correct JDK settings in your IDE.

- You would also need to set git-crypt to be able to decrypt secret keys on your local machine. Follow the instructions.
  here and get back to the project owners if these are not sufficient:
    - [README.SECRETS.md](./README.SECRETS.md)
- To test if the setup is correct.
  - Open this project from IntelliJ.
  - Then Do a dry run on test in : test -> java -> TestSandbox class and see if your setup is correct.


## Test goals and objectives

A good guideline to test any project is:

>> Test the right things, in the right order, at the right time.

- [ ] **Risk based testing** (Test the right things).
  - Identify the most important flows to test first.
- [ ] **Follow test pyramid** (in the right order)
  - What you can cover at unit or API level, should not be covered in the UI level.
- [ ] **Fail fast, fail early** (at the right time)
  - Run tests in CI (pre merge PR, at merge PR, or post merge PR)

## Test framework goals and objectives

Some of the key goals and objectives of our test framework are:

- [ ] **Easy to understand** (Separate test intentions from implementation)
- [ ] **Easy to maintain** (Separation of concerns between data, config, code and tests).
- [ ] **Easy to scale** (Prefer composition over inheritance. Follow SOLID principles).
- [ ] **Fast execution time** (Create atomic, independent tests that can run in parallel to cut down on execution times).
- [ ] **Test at right moment** (Have various CI workflows that allow testing as soon as possible).
- [ ] **Reliable, robust tests** (Create generic classes that have methods that wait for the right state before acting on elements)
- [ ] **Flexible tests that can run on any test env** (Move all env related information to its own config files, so that tests can flexibly run on any test environment)

## Important files

There are some standard files, there deserves a section of their own. These are:

- [x] **README.md file** (This is where you tell what your project is all about and how others can use it.).
- [ ] **junit-platform.properties file** (This is where you specify your projects execution mode i.e. serial, or any of the available parallel execution modes)
- [ ] **.log4j properties file**. (This specifies the log level for your project)
- [ ] **application.conf and other conf file**. (This is where we specify common application config and config for each test environment)
- [ ] **.gitignore file**. (This contains everything that you are *not* interested in version controlling.)
- [ ] **.gitattributes file**. (This is where you specify attributes of any files that are being version controlled.)
- [ ] **.editorconfig file**. (This provides a way to have a common formatting rules within your team. In absence of this, your PRs would be a mess to review.)
- [ ] **pom.xml file**. (This is where you define all your maven project dependencies.)
- [ ] **LICENSE file**. (This is where you give permission to others to make use of your open source project.)
- [ ] **Dockerfile**. (This is where you automate your test environment. i.e. all the parts that your project depends on; such as having a machine, JDK, Maven, setting up system environments, any other tools, etc all.)
- [ ] **.dockerignore file**. (Like gitignore file, here we specify everything that we want to be ignored from passing to docker build context.)
- [ ] **docker-compose.yml file**. (A convenient way to set up a local instance of your dockerized application on your localhost machine. )

## Tool Set

Key tools used in this framework are:

- [ ] **[Selenium](https://www.selenium.dev/)**  (library for Browser automation)
- [ ] **[Java 11](https://openjdk.java.net/projects/jdk/11/)** (as the core programming language)
- [ ] **[Maven 3.8.5](https://maven.apache.org/download.cgi)** (for dependency management)
- [ ] **[Junit 5.8.2](https://junit.org/junit5/docs/current/user-guide/)** (for assertions)
- [ ] **[Typesafe 1.4.2](https://github.com/lightbend/config)** (for application config)
- [ ] **[Lombok 1.18.22](https://projectlombok.org/)** (for java annotations)
- [ ] **[Slf4J 2.0.0-alpha7](https://www.slf4j.org/)** (Simple Logging Facade for Java)
- [ ] **[Log4J2 2.17.2](https://logging.apache.org/log4j/2.x/)** (An underlying logging library - to be used by Slf4J)
- [ ] **Surefire** (for xml reports in CI)
- [ ] **Surefire Site plugin** (for html reports in CI)
- [x] **Github** (for version control)
- [ ] **Github actions** (for continuous integration)
- [ ] **Faker library** (for generating random test data for different locales - germany, france, netherlands, english)
- [ ] **Slack integration** (for giving notifications on pull requests)
- [ ] **Elastic and Kibana** (for test monitoring)
- [ ] **Docker** (for automating test framework's environment)
- [ ] **Powershell or bash Script** (for automating building test environment)
- [ ] **SonarQube/SonarLint** (for keeping your code clean and safe)
- [ ] **Badges** (for a quick view on your project meta and build status)
  Key tools that we will use in other frameworks, that will all extend this core framework are:

## Github workflows

- [how-to-unlock-secrets](https://github.com/sliteteam/github-action-git-crypt-unlock)
- [how-to-cache-maven-dependencies-between-builds-in-github-action](https://github.com/skjolber/maven-cache-github-action)

## References
