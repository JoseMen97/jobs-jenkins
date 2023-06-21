import org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval

ScriptApproval scriptApproval = ScriptApproval.get()
scriptApproval.pendingScripts.each {
    scriptApproval.approveScript(it.hash)
}

folder('proyect_liquibase') {
    displayName('proyect_liquibase2')
    description('Folder for project Liquibase')
}
