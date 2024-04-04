from flask import Flask, request, jsonify
import whisper
import os
import logging

app = Flask(__name__)
model = whisper.load_model("base")

logging.basicConfig(level=logging.DEBUG)

@app.route('/transcribe', methods=['POST'])
def transcribe_audio():
    app.logger.debug('Received a request')

    if 'file' not in request.files:
        return jsonify({"error": "no file provided"}), 400

    file = request.files['file']
    if file.filename == '':
        return jsonify({"error": "no file selected"}), 400

    if file:
        filepath = os.path.join("/tmp", file.filename)
        file.save(filepath)
        
        result = model.transcribe(filepath)
        
        os.remove(filepath)

        return jsonify({"text": result["text"]})

    return jsonify({"text": "unexpected error"}), 500

def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()

@app.get('/shutdown')
def shutdown():
    shutdown_server()
    return 'Server shutting down...'

if __name__ == '__main__':
    app.run(debug=False, port=5001)
