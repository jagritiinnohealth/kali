# Working with git-crypt to encrypt secrets

- [how-to-manage-your-secrets-with-git-crypt](https://dev.to/heroku/how-to-manage-your-secrets-with-git-crypt-56ih)
- [generating-a-new-gpg-key](https://docs.github.com/en/authentication/managing-commit-signature-verification/generating-a-new-gpg-key)
- [add-base64-GIT_CRYPT_KEY-to-github-secrets](https://github.com/sliteteam/github-action-git-crypt-unlock)
  - You can do this by adding a variable `GIT_CRYPT_KEY` to github Secrets, with value that you get from terminal after running command:

    ```bash
    git-crypt export-key ./newkey.asc && cat ./newkey.asc | base64
    ```

[Back to readme file](./README.md)
