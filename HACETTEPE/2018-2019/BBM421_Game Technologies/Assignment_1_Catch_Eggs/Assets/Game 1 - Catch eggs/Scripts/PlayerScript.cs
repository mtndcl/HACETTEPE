using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using System;

public class PlayerScript : MonoBehaviour
{

    public int theScore = 0;
    public float Currenthealth { get; set; }
    public float Maximunthealth { get; set; }


    public float Maximultipower { get; set; }
    public float Currentmultipower { get; set; }

    public float Maxhugepower { get; set; }
    public float Currenthugepower { get; set; }

    public Slider myhealthbar;

         public Slider myhugebar;

  


    public Slider mymultipowerbar;
    private float inittime;

    private float passedtime;






    public GameObject gameOverPanel;
    public Text gameOverText;

    public Boolean gameover;

    public Boolean eggday = false;


    public GameObject combopanel;
    public Text combotext;

    public GameObject startpanel;


    public GameObject youwin;
    public Text youwintext;
    public AudioSource[] sounds = new AudioSource[6];




    private GUIStyle guiStyle = new GUIStyle();


    private float scaledinit;

    private float scaledpassed;

    private Boolean scaled = false;

    private int level;
    GameObject obj;
    void Start()
    {


        obj = GameObject.FindGameObjectWithTag("spawn");
        level = 0;
        sounds[0].Play();
        eggday = false;
        gameOverPanel.SetActive(false);
        combopanel.SetActive(false);
        youwin.SetActive(false);
        gameover = true;
        scaledinit = Time.time;
        inittime = Time.time;
        Maximunthealth = 100f;
        Currenthealth = Maximunthealth;
        myhealthbar.value = Currenthealth / Maximunthealth;
        

        Maximultipower = 200f;
        Currentmultipower = 0f;
        mymultipowerbar.value = Currentmultipower / Maximultipower;

        Maxhugepower = 100f;
        Currenthugepower = 0f;
        myhugebar.value = Currenthugepower / Maxhugepower;






    }
    void Update()
    {





        if (!gameover)
        {



            MovePlayer();

            SetBars();

            PlayerInputs();


            CheckGameOver();


            ControlScaled();

            CheckYouwin();
        }
        else
        {








        }













    }

    private void CheckYouwin()
    {

        if (level>17)
        {


            youwin.SetActive(true);
            youwintext.text = "YOU  WIN THE GAME CONGRATULATIONS!!!";

            gameover = true;

        }
    }

    private void ControlScaled()
    {


        if (scaled)
        {

            scaledpassed = Time.time - scaledinit;

            if (scaledpassed>3)
            {

                transform.localScale = new Vector3(0.1045929f , 0.02398696f , 0.1045928f );
                scaled = false;
            }

        }
    }

    private void MovePlayer()
    {

        float moveInput = Input.GetAxis("Horizontal") * Time.deltaTime * 3;
        transform.position += new Vector3(moveInput, 0, 0);


        if (transform.position.x <= -2.5f || transform.position.x >= 2.5f)
        {
            float xPos = Mathf.Clamp(transform.position.x, -2.5f, 2.5f);
            transform.position = new Vector3(xPos, transform.position.y, transform.position.z);
        }
    }

    private void SetBars()
    {
        passedtime = Time.time - inittime;
        if (passedtime > 0.1)
        {
            Currenthealth -= 1f;
            Currentmultipower += 1f;
            Currenthugepower += 1f;
            myhealthbar.value = Currenthealth / Maximunthealth;
            mymultipowerbar.value = Currentmultipower / Maximultipower;
            myhugebar.value = Currenthugepower / Maxhugepower;
            inittime = Time.time;
        }
    }

    private void CheckGameOver()
    {


        if (Currenthealth < 0 && !gameover)
        {





            combopanel.SetActive(false);
            gameover = true;


            gameOverText.text = "Game over Score " + theScore.ToString() + " ";


            gameOverPanel.SetActive(true);

          

            // GameObject bucket1 = (GameObject)Instantiate(bucketprefab, transform.position + new Vector3(2, 0, 0), Quaternion.identity);


            Currenthealth = Maximunthealth;
        }

    }

    private void PlayerInputs()
    {






        if (Input.GetKeyDown(KeyCode.Space) && mymultipowerbar.value > 0.5)
        {



            sounds[4].Play();
         
            eggday = true;
            Currentmultipower = 0;

        }
        if (Input.GetKeyDown(KeyCode.LeftShift)  &&  myhugebar.value>0.9)
        {

            sounds[5].Play();
            scaled = true;
            scaledinit = Time.time;
            transform.localScale = new Vector3(0.1045929f*3/2, 0.02398696f*3/2, 0.1045928f*3/2);

            Currenthugepower = 0;


        }




    }

    void OnGUI()
    {

        level = (int)(16 - obj.GetComponent<SpawnerScript>().gamelevel * 10);
        guiStyle.fontSize = 20;
        guiStyle.alignment = TextAnchor.UpperCenter;
        GUILayout.Label("Speed: "+ level, guiStyle);
        guiStyle.alignment = TextAnchor.UpperLeft;
        GUILayout.Label("Score: " + theScore, guiStyle);
    }
}
