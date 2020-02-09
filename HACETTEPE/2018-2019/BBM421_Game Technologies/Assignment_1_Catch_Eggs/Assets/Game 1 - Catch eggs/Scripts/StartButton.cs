using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class StartButton : MonoBehaviour
{
    GameObject obj;
    GameObject obj0;
   

    public Button start_button;
    void Start()
    {


        obj = GameObject.FindGameObjectWithTag("bucket");
        obj0 = GameObject.FindGameObjectWithTag("spawn");
      
        start_button.onClick.AddListener(startbuttonlistener);

    }

    // Update is called once per frame
    void Update()
    {
        
    }


    void startbuttonlistener()
    {
        obj.GetComponent<PlayerScript>().sounds[2].Play();
       
      
        obj.GetComponent<PlayerScript>().startpanel.SetActive(false);
        obj.GetComponent<PlayerScript>().gameover = false;

    }

   
}
