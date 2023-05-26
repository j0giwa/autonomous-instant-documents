#!/usr/bin/env python3

import os
from shutil import copytree


def detectOS() -> str:
    return os.name

def getAssetLocation(operatingSystem: str) -> str:
    if operatingSystem == "posix":
        latexSnippetDefaultlLocation = os.environ['XDG_CONFIG_HOME'] + "/aids"
    elif operatingSystem == "nt":
        latexSnippetDefaultlLocation = os.environ['appdata'] + "/aids"
    return latexSnippetDefaultlLocation

def copyDefaultsToLocation(defaults: str, destiantion: str):
    if (os.path.isdir(destiantion)):
        return
    copytree(defaults, destiantion)

def constructLatexRefferenceFile(texDirectory: str, destination: str):
    headerfilePath = texDirectory + "/latex/test/header.tex"
    chapterfilePaths = next(os.walk(texDirectory + "/latex/test/chapters"), (None,None,[]))[2] # []if no file 
    footerfilePath = texDirectory + "/latex/test/footer.tex"
    with open(destination, 'w') as outfile:
        with open(headerfilePath) as infile:
            for line in infile:
                outfile.write(line)
        outfile.write("\n")
        for chapterfilePath in chapterfilePaths:
            outfile.write("\\input{" + texDirectory + "/latex/test/chapters/" + chapterfilePath + "}\n")
        with open(footerfilePath) as infile:
            for line in infile:
                outfile.write(line)
            outfile.write("\n")

if __name__ == "__main__":
    operatingSystem = detectOS()
    assetLocation = getAssetLocation(operatingSystem)
    copyDefaultsToLocation("./assets/defaults", assetLocation)
    constructLatexRefferenceFile(assetLocation, "./src/test/resources/latex/test/test.tex")
