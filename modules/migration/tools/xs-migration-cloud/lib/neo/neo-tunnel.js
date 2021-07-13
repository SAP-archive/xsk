const path = require('path');
const { execSync } = require('child_process');

const OPEN_TUNNEL_UNIX_SCRIPT_FILE_NAME = "neo-tunnel.sh";
const NEO_TOOLS_FOLDER_PATH = '/tools/';

const generatePathToScript = (scriptFileName) => {
  return path.join(path.dirname(module.filename), '../..', NEO_TOOLS_FOLDER_PATH, scriptFileName);
}

class NeoTunnelUtils {

  static openTunnel(system) {
    const scriptPath = generatePathToScript(OPEN_TUNNEL_UNIX_SCRIPT_FILE_NAME);

    const { account, host, user, password, db} = system.neo;
    const script = `bash ${scriptPath} -a "${account}" -h "${host}" -u "${user}" -p "${password}" -i "${db}"`;

    const result = execSync(script);

    return JSON.parse(result.toString());
  }

  static closeTunnel(tunnelId) {
    const scriptPath = generatePathToScript(OPEN_TUNNEL_UNIX_SCRIPT_FILE_NAME);

    execSync(`bash ${scriptPath} -e close -t ${tunnelId}`);
  }

  static isTunnelRequired(system) {
    return system.neo.isActive && system.neo.isActive === 'true';
  }
}

module.exports = NeoTunnelUtils;