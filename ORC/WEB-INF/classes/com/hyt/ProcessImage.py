from PIL import Image,ImageDraw,ImageFont
from zipfile import ZipFile
from pytesseract import Output
import pytesseract
import cv2
import json
import sys
import re
import os
import numpy
import pathlib
import math


class ProcessImage:
 def __init__(self,jsonString):
   self.jsonn=jsonString

 def getImageData(self,path):
  dct={}
  dct.update(template='output.jpg')
  dct.update(data=[])
  values=self.jsonn.values();
  image=cv2.imread(path+"/images/"+sys.argv[1], 1)
  data=pytesseract.image_to_data(image,output_type=Output.DICT)
  file=open('data.txt','w');
  file.write(str(data));
  file.close();
  for key in range(1,100,1):
   if(str(key) in data['text']):
    index=data['text'].index(str(key))
    rleft=data['left'][index]
    rtop=data['top'][index]
    rheight=data['height'][index]
    rwidth=data['width'][index]
  
    index=index-2;
    dtop=data['top'][index]
  
    image[rtop:rtop+rheight, rleft:rleft+rwidth] = 255
    dct['data'].append({key:"","x":rleft,"y":dtop-5})
  if(os.path.isdir(path+'/data')==False):
    os.mkdir(path+'/data')
  cv2.imwrite(path+'/data/output.jpg', image)
  f=open(path+'/data/data.json','w')
  json.dump(dct,f,indent=4)
  f.close()

class ImageInformation:
 def __init__(self):
  self.template=""
  self.data=[]

 def drawPixels(self,process,path):
  dct={}
  dct.update(template='output.jpg')
  dct.update(data=[])

  file=open(path+'/data/data.json','r')
  imageInformation.__dict__=json.load(file)  
  datalist=list(process.jsonn.keys())
  print("DataList : ", datalist)
  valuelist=list(process.jsonn.values())
  print("ValueList : ", valuelist)
  slicevl=[slv[1][2:] for slv in valuelist]
  print("slicevl : ", slicevl)
  fontlist=[font[0] for font in valuelist]
  print("FontList : ", fontlist)
  image=cv2.imread(path+'/data/output.jpg',1)
   
  maxReplacement=20;
  mappingList=[n[1][0:1] for n in valuelist]
  print("MappingList : ",mappingList)
  for h in range(0,maxReplacement,1):
    font_type = ImageFont.truetype("calibri.ttf", 86) 
    image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    img = Image.fromarray(image)
    draw = ImageDraw.Draw(img)
    if(str(h+1) in mappingList):
     index=mappingList.index(str(h+1))
     fontsize=math.ceil((60*int(fontlist[index]))/14)
     font_type = ImageFont.truetype("calibri.ttf", fontsize); 
     dct['data'].append({h+1:slicevl[index],"x":imageInformation.data[h]['x'],"y":imageInformation.data[h]['y']})
     draw.text(xy=(imageInformation.data[h]['x'], imageInformation.data[h]['y']), text= slicevl[index], fill =(0,0,0), font = font_type)
    image=numpy.array(img)
  f=open(path+'/data/data.json','w')
  json.dump(dct,f,indent=4)
  f.close()
  cv2.imwrite(path+'/data/output.jpg', image)

class Utility:
 def zipIt(self,path):
  zipObj = ZipFile(path+'/data/data.zip', 'w')
  #zipObj.write(path+'/data/data.json','data.json')
  zipObj.write(path+'/data/output.jpg','output.jpg')
  zipObj.close();


if(__name__=='__main__'):
 print(end='\n')
 print("#############################################")
 print(end='\n')
 path=str(pathlib.Path(__file__).parent.absolute())
 path=path[0:path.find('\WEB-INF')]
 file=open(path+'/data/data.json')
 jsonString=json.load(file)
 processImage=ProcessImage(jsonString);
 processImage.getImageData(path)
 imageInformation=ImageInformation()
 imageInformation.drawPixels(processImage,path)
 Utility().zipIt(path)
