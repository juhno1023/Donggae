import { useNavigate } from "react-router-dom";
import styles from "./Post.module.css"
import Header from "../_Layout/Header";

export default function Post() {
  return (
    <div className={styles.default}>
        <Header />
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                <button type="submit">지원하기</button>
                <div  className={styles.text__1} for="team_name" >이번 학기에 소공 같이 플젝 하실 분 모집합니다</div>
                <div className={styles.formGroup}>
                    <div>이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs 입니다
                        </div>
                </div>
            </div>
            <div className={styles.box__}>
                <div className={styles.half}>
                <div className={styles.text__1}>팀장 정보</div>

                </div>
                <div className={styles.half}>
                    <div className={styles.text__1}>팀장이 모집하고자 하는 팀원 및 기술 스택 </div>
                    <div className={styles.text__2}>모집 분야</div>
                    <div className={styles.container}>
                        <div>
                            <label>
                                <input type="checkbox" name=""/>
                                <span class="list">전체</span>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input type="checkbox" name=""/>
                                <span class="list">JavaScript</span>
                            </label>
                        </div>
                    </div>
                    <div className={styles.text__2}>선호 언어</div>
                    <div className={styles.container}>
                        <div>
                            <label>
                                <input type="checkbox" name=""/>
                                <span class="list">전체</span>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input type="checkbox" name=""/>
                                <span class="list">JavaScript</span>
                            </label>
                        </div>
                    </div>
                    <div className={styles.text__2}>선호 성향</div>
                    <div className={styles.container}>
                        <div>
                            <label>
                                <input type="checkbox" name=""/>
                                <span class="list">전체</span>
                            </label>
                        </div>
                        <div>
                            <label>
                                <input type="checkbox" name=""/>
                                <span class="list">JavaScript</span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
);
}