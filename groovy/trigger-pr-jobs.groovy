import jenkins.model.Jenkins

new_prs_file = hudson.model.Executor.currentExecutor().getCurrentWorkspace().child('new-prs.txt')
if ( new_prs_file.length() > 0 ) {
    new_prs_file.eachLine { pr_id ->
        pr_job = Jenkins.instance.getJob('libnacl').getJob('pr').getJob("${pr_id}").getJob('main-build')
        trigger = job.triggers.iterator().next().value
        repo = trigger.getRepository()
        pr = repo.getPullRequest(pr_id)
        repo.check(pr)
        pr = repo.pulls.get(pr_id)
        repo.helper.builds.build(pr, pr.author, 'Job Created. Start Initial Build')
    }
}
