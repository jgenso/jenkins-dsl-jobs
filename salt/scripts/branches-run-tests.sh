# Random 1-10 seconds sleep to split the server load
sleep $(($RANDOM % 10))

salt-jenkins-build \
  --output-columns=160 \
  --vm-source ${JENKINS_VM_SOURCE} \
  --cloud-deploy \
  --test-prep-sls=projects.salt.${BRANCH_NAME}.unit \
  --test-git-commit ${GIT_COMMIT} \
  --bootstrap-salt-commit ${SALT_MINION_BOOTSTRAP_RELEASE} \
  --test-default-command \
  --build-packages
