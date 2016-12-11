package edu.bsuir.beans;

import edu.bsuir.DAO.AbstractDAO;
import edu.bsuir.DAO.FilmDAO;
import edu.bsuir.DAO.PictureDAO;
import edu.bsuir.model.Film;
import edu.bsuir.model.FilmView;
import edu.bsuir.model.FilterView;
import edu.bsuir.model.Picture;
import edu.bsuir.utils.ParseUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FilmView selectedFilm;

	private String name;
	private Date startDuration;
	private Date endDuration;
	private String[] genre;
	private String country;
	private String year;
	private Date startTime;

	/**
	 * @return
	 * @throws IOException
	 */
	public List<FilmView> getFilms() throws IOException {

		if (name != null && startDuration != null && endDuration != null && genre != null &&
				country != null && year != null && startTime != null) {

			SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
			String startDurationString = localDateFormat.format(startDuration);
			String endDurationString = localDateFormat.format(endDuration);
			String startTimeString = localDateFormat.format(startTime);

			Year yearObj = Year.of(Integer.parseInt(year));

			AbstractDAO<Film> filmDAO = new FilmDAO();
			AbstractDAO<Picture> pictureAbstractDAO = new PictureDAO();

			List<Film> filmList = filmDAO.getResult(new FilterView(name, Time.valueOf(startDurationString),
					Time.valueOf(endDurationString), genre, country, yearObj, Time.valueOf(startTimeString)));
			List<FilmView> filmViewList = new ArrayList<FilmView>();
			for (Film film : filmList) {
				filmViewList.add(new FilmView(film, pictureAbstractDAO.get(film.getPictureId())));
			}
			ParseUtil.writeIntoExcel(filmList);

			return filmViewList;
		} else
			return null;
	}

	public void execute() {
		try {
			if (name != null && startDuration != null && endDuration != null && genre != null &&
					country != null && year != null && startTime != null) {

				SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
				String startDurationString = localDateFormat.format(startDuration);
				String endDurationString = localDateFormat.format(endDuration);
				String startTimeString = localDateFormat.format(startTime);

				Year yearObj = Year.of(Integer.parseInt(year));

				AbstractDAO<Film> filmDAO = new FilmDAO();

				List<Film> filmList = filmDAO.getResult(new FilterView(name, Time.valueOf(startDurationString),
						Time.valueOf(endDurationString), genre, country, yearObj, Time.valueOf(startTimeString)));
				if (filmList.isEmpty()) {
					FacesMessage message = new FacesMessage("Подтверждение", "Нет");
					message.setSeverity(FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().addMessage(null, message);
				} else {
					FacesMessage message = new FacesMessage("Подтверждение", "Да");
					message.setSeverity(FacesMessage.SEVERITY_INFO);
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			} else {
				FacesMessage message = new FacesMessage("Warning", "Не все поля заполнены");
				message.setSeverity(FacesMessage.SEVERITY_WARN);
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		} catch (Exception ex) {
			FacesMessage message = new FacesMessage("Error", "Проблема с вводимыми полями");
			message.setSeverity(FacesMessage.SEVERITY_FATAL);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public FilmView getSelectedFilm() {
		return selectedFilm;
	}

	public void setSelectedFilm(FilmView selectedFilm) {
		this.selectedFilm = selectedFilm;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDuration() {
		return startDuration;
	}

	public void setStartDuration(Date startDuration) {
		this.startDuration = startDuration;
	}

	public Date getEndDuration() {
		return endDuration;
	}

	public void setEndDuration(Date endDuration) {
		this.endDuration = endDuration;
	}

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}