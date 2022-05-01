# Secrets

Our secret keys are saved in `secrets.*` and encrypted with [`git-crypt`](https://github.com/AGWA/git-crypt#readme).

## References: Working with git-crypt to encrypt secrets

- [how-to-manage-your-secrets-with-git-crypt](https://dev.to/heroku/how-to-manage-your-secrets-with-git-crypt-56ih)
- [generating-a-new-gpg-key](https://docs.github.com/en/authentication/managing-commit-signature-verification/generating-a-new-gpg-key)
- [add-base64-GIT_CRYPT_KEY-to-github-secrets](https://github.com/sliteteam/github-action-git-crypt-unlock)
  - You can do this by adding a variable `GIT_CRYPT_KEY` to github Secrets, with value that you get from terminal after running command:

    ```bash
    git-crypt export-key ./newkey.asc && cat ./newkey.asc | base64
    ```

[Back to readme file](./README.md)

## [wip] This is rough take away from another reference and thus needs some clean up to make it more robust and

relevant for us.

## Gaining Access to Secrets

### I. (New User) Generate and Share your GPG Key

1. Verify if `gpg` is already installed on your system with `gpg --version`. If not;
2. Install `gpg` on your system:
    - macOS (with [homebrew](https://brew.sh/)): `brew install gnupg`
    - Linux (<https://docs.microsoft.com/en-us/windows/wsl/install-win10>): `sudo apt install gnupg`
    - [Windows WSL: Open your wsl (default is Ubuntu) from start menu (not to run this cmd from powershell or git bash)](https://docs.microsoft.com/en-us/windows/wsl/install-win10): `sudo apt install gnupg`
2. Follow [these directions from GitHub](https://docs.github.com/en/github/authenticating-to-github/managing-commit-signature-verification/generating-a-new-gpg-key) to generate a new gpg key.
    - Your key should begin with `-----BEGIN PGP PUBLIC KEY BLOCK-----` and end with `-----END PGP PUBLIC KEY BLOCK-----` - if it doesn't you have the wrong data!
3. Share your public key with someone whose key has already been added and ask them to complete Step `II` below:

### II. (Existing User) Add the New User to `git-crypt`
**These steps can be completed by anyone whose `gpg` key has been added and who has `Maintain` permissions or higher.**
1. Save the public key you receive as `newkey.asc`.
2. Run `gpg --import newkey.asc` to import the key into your local gpg instance.
3. Run `gpg --list-keys` and make note of the key's ID (will be a string of 40 hex characters) and email address.
4. Run `gpg --edit-key <KEY_ID>` to open the `gpg` console ([source](https://stackoverflow.com/questions/33361068/gnupg-there-is-no-assurance-this-key-belongs-to-the-named-user#answer-34132924)):
    - Type `trust` to edit the key's trust level
    - Type `5` to indicate you trust the key and then `y`.
    - Type `quit` to exit the `gpg` console.
5.  Double-check to ensure that you're on the branch you want to commit the change in permissions to.
6.  Run `git-crypt add-gpg-user <USER_EMAIL_ADDRESS>` to add the new user to `git-crypt`.
7.  The changes will automatically be `commit`ted, but you must run `git push` to push them to remote.

### III. (New User) Install and Set Up `git-crypt`
1. Install `git-crypt` on your system:
    - macOS (with homebrew) `brew install git-crypt`
    - Linux or Windows WSL `sudo apt install git-crypt`
    - [Manual installation](https://github.com/AGWA/git-crypt/blob/master/INSTALL.md)
2. Clone this repo. If you've already cloned it, be sure to `git pull` once the admin indicates they've successfully added your `gpg` key.
3. Run `git-crypt unlock` to decrypt the secret files. You only need to perform this step once.
    - For Windows users, you have to run this command from git bash (running it from the powershell would not work).
4. Secret files will now be automatically encrypted and decrypted when `commit`ting / `pull`ing them.

## Saving New Secrets
With `git-crypt` set up, you can safely commit new keys directly to `secrets.*` files without fear of exposing any secret information.
- Please add comment headers between sections of unrelated secrets
- Please only save *actually secret* info in these files. Non-secret environment variables (e.g. `NODE_ENV`) should have their defaults set in `src/config` instead.

## Saving Local Backups
You can create local backups of env files you don't want to commit in `secrets.*.backup` and `secrets.*.*.backup` files, as they will be ignored by `git`.

## Managing Merge Conflicts
It isn't possible to view `diff`s on secret files. As a result, the newest version will overwrite any older versions. To avoid accidentally deleting keys from other branches:
1. Save a copy of your `secrets.*` to `secrets.*.backup`.
2. Retrieve the latest `secrets.*` from your target branch.
3. Copy-paste any keys present in the target branch and *not* in yours to your `secrets.*.backup`.
4. Commit the combined file as `secrets.*` in your branch.

## Managing Secrets across Environments
- Save a copy of the value you want to use in local environments directly in `secrets.*`
- If you want to use other values in other environments, save them in GitHub Secrets
- Update the secret-replacement logic in build workflows (e.g. [test-the-tester.yml](.github/workflows/test-the-tester.yml)) to include your secrets
