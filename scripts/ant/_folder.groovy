import org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval

ScriptApproval scriptApproval = ScriptApproval.get()
scriptApproval.pendingScripts.each {
    scriptApproval.approveScript(it.hash)
}

folder('ant_multi') {
    displayName('ant_multi')
    description('Folder for project ant_multi')
}
