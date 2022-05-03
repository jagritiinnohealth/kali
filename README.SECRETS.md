# Secrets

Our secret keys are saved in `secrets.*` files and encrypted with [`git-crypt`](https://github.com/AGWA/git-crypt#readme).

> A user who is not added to the project, will not be able to use the secrets from our project and thus this is a mandatory
step to complete to be able to run the tests.

## Section1: One time setup

## Step1: Verify if you have git crypt installed on your system

- macOS/Linux (run from terminal): `git-crypt --version`
- Windows (run from gitbash or powershell): `git-crypt --version`

## Step2: Install git crypt, if you haven`t already

> If you already see git-crypt installed in the previous step, skip.

Install `git-crypt` on your system:

- macOS (with homebrew) `brew install git-crypt`
- Linux or Windows WSL `sudo apt install git-crypt`
- [Manual installation](https://github.com/AGWA/git-crypt/blob/master/INSTALL.md)

## Step3: Verify if `gpg` is already installed on your system

- For Linux and macOS users;
  - Run `gpg --version` on a terminal and see if you get a version.
- For Windows users;
  - [Windows WSL: Open your wsl (default is Ubuntu) from start menu (not to run this cmd from powershell or git bash)](https://docs.microsoft.com/en-us/windows/wsl/install-win10)
  - Run `gpg --version` on wsl (ubuntu) terminal and see if you get a version.

## Step4: If not installed already, Install `gpg` on your system

> If already installed, skip this step.

Install for:

- macOS (with [homebrew](https://brew.sh/)): `brew install gnupg`
- Linux: `sudo apt install gnupg`
- Windows: with ([WSL: Open your wsl (default is Ubuntu) from start menu (not to run this cmd from powershell or git bash)](https://docs.microsoft.com/en-us/windows/wsl/install-win10): `sudo apt install gnupg`

## Step5: Verify [existing gpg keys on your system](https://docs.github.com/en/authentication/managing-commit-signature-verification/checking-for-existing-gpg-keys)

- macOS/Linux (run from terminal): `gpg --list-keys`
- Windows (run from gitbash - not powershell): `gpg --list-keys`

> If you already see any keys associated for the email you want to create keys, you can skip the next step. If not, continue.

## Step6: If not already available, [generate a new gpg key](https://docs.github.com/en/authentication/managing-commit-signature-verification/generating-a-new-gpg-key)

- Follow [these directions from GitHub](https://docs.github.com/en/github/authenticating-to-github/managing-commit-signature-verification/generating-a-new-gpg-key) to generate a new gpg key.
- At the end of above process, you should have a key that begin with `-----BEGIN PGP PUBLIC KEY BLOCK-----` and end with `-----END PGP PUBLIC KEY BLOCK-----`
- Save this key in a file (say) `newkey.asc`. You will be asked to share this key in step 8.
- If not you have the wrong data! Repeat this step carefully again.

## Step7: Add this GPG key to your github account

> [skip this step for now. I want to see if it is really mandatory]

- Follow [these directions to add gpg key to your github account](https://docs.github.com/en/authentication/managing-commit-signature-verification/adding-a-new-gpg-key-to-your-github-account).

## Step8: Share your public key with someone whose key has already been added and ask them to add your key to trusted list of keys

- Share the key you saved in step 4 `newkey.asc` with your team member who has already set this up.

## Step9: (Existing User) Add gpg keys to trusted list of gpg keys and also add to git-crypt

### Step (i): Add another users gpg key to trusted list of gpg keys

**These steps can be completed by anyone whose `gpg` key has been added and who has `Maintain` permissions or higher.**

From terminal or from gitbash:

1. Save/copy the public key you received as `newkey.asc` in the root of your terminals location.
2. Run `gpg --import newkey.asc` to import the key into your local gpg instance.
3. Run `gpg --list-keys` and make note of the key's ID (will be a string of 40 hex characters) and email address.
4. Run `gpg --edit-key <KEY_ID>` to open the `gpg` console ([source](https://stackoverflow.com/questions/33361068/gnupg-there-is-no-assurance-this-key-belongs-to-the-named-user#answer-34132924)):
    - Type `trust` to edit the key's trust level
    - Type `5` to indicate you trust the key and then `y`.
    - Type `quit` to exit the `gpg` console.

### Step (ii): Go to project you want to add this user to and create a new branch to commit this keys to

1. Double-check to ensure that you're on the branch you want to commit the change in permissions to.
2. Run `git-crypt add-gpg-user <USER_EMAIL_ADDRESS>` to add the new user to `git-crypt`.
3. The changes will automatically be `committed`, but you must run `git push` to push them to remote.

### Step (iii): Delete the `newkey.asc` that another user shared with you

1. To avoid accidentally committing this key to your project or to get it exposed, delete this key. Its purpose is solved and it is no longer required now.

### Step (iv): New User to pull the latest changes from repo

1. Clone this repo. If you've already cloned it, be sure to `git pull` once the admin indicates they've successfully added your `gpg` key.
2. Run `git-crypt unlock` to decrypt the secret files. You only need to perform this step once.
    - For Windows users, you have to run this command from git bash (running it from the powershell would not work).
3. Secret files will now be automatically encrypted and decrypted when `committing` / `pulling` them.

> A good check to see if this worked or not is to check the secrets.* file. For a new user earlier when this was a blob,
> now you should be able to see plain text information.

## Step10: Decrypt secrets in github actions CI, to be able to run tests in CI

- [add-base64-GIT_CRYPT_KEY-to-github-secrets](https://github.com/sliteteam/github-action-git-crypt-unlock)
- You can do this by adding a variable `GIT_CRYPT_KEY` to github Secrets, with value that you get from terminal after running command:

  ```bash
  git-crypt export-key ./newkey.asc && cat ./newkey.asc | base64
  ```

## References: Working with git-crypt to encrypt secrets

- [how-to-manage-your-secrets-with-git-crypt](https://dev.to/heroku/how-to-manage-your-secrets-with-git-crypt-56ih)
- [generating-a-new-gpg-key](https://docs.github.com/en/authentication/managing-commit-signature-verification/generating-a-new-gpg-key)

[Back to readme file](./README.md)

## Section2: Working with secrets

## Saving New Secrets

With `git-crypt` set up, you can safely commit new keys directly to `secrets.*` files without fear of exposing any secret information.

- Please add comment headers between sections of unrelated secrets
- Please only save *actually secret* info in these files. Non-secret environment variables (e.g. `RESULTS_DIRECTORY`)
  should have their defaults set in `src/main/resources/application.conf` instead.

## Saving Local Backups

You can create local backups of secret files you don't want to commit in `secrets.*.backup` and `secrets.*.*.backup` files,
as they will be ignored by `git`.

## Managing Merge Conflicts

It isn't possible to view `diff`s on secret files. As a result, the newest version will overwrite any older versions.
To avoid accidentally deleting keys from other branches:

1. Save a copy of your `secrets.*` to `secrets.*.backup`.
2. Retrieve the latest `secrets.*` from your target branch.
3. Copy-paste any keys present in the target branch and *not* in yours to your `secrets.*.backup`.
4. Commit the combined file as `secrets.*` in your branch.

## Managing Secrets across Environments

- Save a copy of the value you want to use in local environments directly in `secrets.*`
- If you want to use other values in other environments, save them in GitHub Secrets
- Update the secret-replacement logic in build workflows (e.g. [test-the-tester.yml](.github/workflows/test-the-tester.yml))
  to include your secrets
